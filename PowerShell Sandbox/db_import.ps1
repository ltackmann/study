[void][system.reflection.Assembly]::LoadFrom("C:\Program Files (x86)\MySQL\Connector NET 6.7.4\Assemblies\v2.0\MySQL.Data.dll")
$cn = New-Object -TypeName MySql.Data.MySqlClient.MySqlConnection
$cn.ConnectionString = "SERVER=localhost;DATABASE=db_name;UID=db_user;PWD=db_password"
$cn.Open()

function InsertData {
	$user_db = Get-Content .\data_file.txt
	$user_db | %{
		if($_ -match '^(.{7})(.{10})(.{40})(.{4})(.{4})(.*)$') {
			$userid = $matches[1]
			$social_security_number = $matches[2]
			$name = $matches[3] 
			$branch = $matches[4]
			$user_access_group = $matches[5]
			$tail = $matches[6]
			$role1 = if ($tail.length -ge 1) { $tail[0] } else { "" }
			$role2 = if ($tail.length -ge 2) { $tail[1] } else { "" }
			$status = if ($tail.length -ge 3) { -join $tail[2..$tail.length] } else { "" }
			
			#"INSERT INTO users (userid, social_security_number, name, branch, user_access_group, role1, role2, status) VALUES (`'$userid`', `'$social_security_number`', `'$name`' ,`'$branch`', `'$user_access_group`', `'$role1`', `'$role2`', `'$status`')"
			$cm = New-Object -TypeName MySql.Data.MySqlClient.MySqlCommand
			$cm.CommandText = "INSERT INTO users (userid, social_security_number, name, branch, user_access_group, role1, role2, status) VALUES (`'$userid`', `'$social_security_number`', `'$name`' ,`'$branch`', `'$user_access_group`', `'$role1`', `'$role2`', `'$status`')"
			$cm.Connection = $cn
			$dr = $cm.ExecuteNonQuery()
		} else {
			$exceptions = @{"z9997774000000009TEST"=""}
			if($exceptions.ContainsKey($_)) {
			} else {
				throw "$_ does not match expected regex"
			}
		}
	}
}

InsertData
