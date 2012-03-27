######################################################
# Build in variables (note these are case insensite) #
######################################################
## Get boolean value to determine if last operation succeded.
$?
## Get exit code of the last operation (Note this only works on
## executables (like ping) that actually returns an exit code)
$lastexitcode
## Get current path      
$pwd
## Get current user home
$home



#################
# Misc commands #
#################
## Clear Terminal (alias for Clear-Host - similar to UNIX "clear")
cls
## Get command aliases (alias for Get-Alias - similar to UNIX "TODO")
alias 
## Get aliases whoes name start with "s"
alias s*
## Get aliases whoes defenition contains the word "Location"
alias | ?{ $_.Definition -like "*Location"}
## Make "cl" alias for "cleartool" (alias for New-Alias - similar to UNIX "TODO")
nal cl cleartool.exe
## Remove "cl" alias (alias for Remove-Item - similar to UNIX "unset")
rm -path alias:cl
## Get current directory (alias for Get-Location - similar to UNIX "pwd")
pwd
## Save current location in directory stack and go to target 
## (alias for Push-Location - similar to unix "pushd")
pushd c:\target
## Goto last location in directory stack 
## (alias for Pop-Location - similar to UNIX "popd")
popd 
## Show directory stack (similar to UNIX "dirs")
pwd -Stack
## Get path to command "PowerShell" 
##(alias for Get-Command - similar to UNIX "which")
gcm PowerShell
## Get commands whoes name contain the word "string"
gcm *string
## Determine type of output from the "alias" command
$a=alias; $a.GetType()
## List all processes (alias for Get-Process - similar to UNIX ps).
gps
## Get "firefox" process and kill it 
gps Firefox | kill
## Open "file.txt" with associated program 
## (alias for Invoke-Item - similar to UNIX TODO)
ii file.txt
## Open explore window in current folder
ii .
## Remove files/folders recursivly
rm -r -f
## Get exit code of last program (echo is alias for Write-output - similar to UNIX echo)
echo $LASTEXITCODE
## Get date in UNIX time (alias for Get-Date - similar to UNIX date)
date -u "%s"
## Create file .hgrc (alias for New-Item - similar to UNIX touch)
ni .hgrc -t file
## Print commandline hostory (alias for Get-History - similar to UNIX TODO)
h
## Get unique output (alias for Select-Object - similar to UNIX uniq)
select -u



##############################################################
# man (or help) (alias for Get-Help - similar to UNIX "man") #
##############################################################
## Show quick help for command dir
man dir
## Show full help for command dir
man dir -full
## Show help for regular expressions in PowerShell
man about_reg



#####################################################################
# ls (or dir) (alias for Get-ChildItem - similar to UNIX "find/ls") #
#####################################################################
## Show enviroment
ls env:
## Show specific env value
ls env:windir # or
echo $env:windir 
## Show HKEY_LOCAL_MACHINE top-level hive of the registry
ls hklm:
## List pom.xml files in current directory 
ls pom.xml
## Find file.txt in current directory open it in gvim
ls file.txt | gvim $$ 
## List pom.xml files recursivly from current directory
ls -r -i pom.xml
## Find all files/folders named ".settings" and ".project" recursivly 
## from the current directory
ls -r -i ".settings",".project"
## Find files named pom.xml files containing text "spring-test" recursivly 
## from the current directory
ls -r -i pom.xml | select-string "spring-test"
## List files edited today
ls | ?{$_.LastWriteTime -ge [DateTime]::Today}
## Count number of lines of the xml files in the current folder
(ls *.xml | select-string .).Count
## List directories in current folder ("where" is alias for Where-Object)
ls | where { $_.PSIsContainer }
## List files in current folder ("?" is alias for Where-Object)
ls | ? { !$_.PSIsContainer }
## List directories containing file named file.txt
ls -r -i file.txt | Split-Path -Parent


#######################
# Regular expressions #
#######################
## Case insensitive regex match
"Microsoft" -match "Soft"
## Case sensitive regex match
"Google" -cmatch "go"
## Print matches
$matches



#############
# Shortcuts #
############# 
##> launch cmd with: "Ctrl + Shift + p"



###############
# Programming #
###############
# Create directory if does not exists
if ( (test-path $dir) -ne $true) {mkdir $dir}
# String concatenation
$str1 + " and " + $str2 # or
"$str1 and $str2"
# Replace dot's with slashes
echo "org.springframework".Replace(".","/")
# Get and Parse XML
$url = "http://blogs.msdn.com/powershell/rss.xml"
[xml]$xml = (new-object System.Net.WebClient).DownloadString($url)
$xml.rss.channel | Foreach {$_.item} |
    Where {[DateTime]$_.pubDate -gt (Get-Date).AddMonths(-1)} |
    Format-Table Title
# Regex group match/split
[regex]$regex = 'x(.+?)'
$values = $regex.matches("x0 x1 y2 x3") | foreach {$_.Groups[1].Value}
echo "$values" # print 0 1 3
# Array containing 1,2,3
$a = 1, 2, 3
# Array containing numbers from 1 to 100 (similar to UNIX command seq).
$a = 1..100
# exlicitly create integer array 
[int[]] $a = 1,2,3,4
# sum array of integers
($a | Measure-Object -Sum).Sum
# get maximum number in array of integers
($a | Measure-Object -Max).Maximum
# define function that return sum and product of array in a object
function Get-SumAndProduct ($a) {
    $sum = 0
    if ($a.Length -eq 0) {
        $prod = 0
    } else {
        $prod = 1
        foreach ($x in $a) {
            $sum += $x
            $prod *= $x
        }
    }
    $ret = New-Object PSObject
    $ret | Add-Member NoteProperty Sum $sum
    $ret | Add-Member NoteProperty Product $prod
    return $ret
}
# XML parsing
$jetty_cfg = [xml](get-content "$jetty_home\etc\jetty.xml")
$cfg = $jetty_cfg.SelectSingleNode("/Configure/Call[1]/Arg/New/Set[2]/SystemProperty");
if($cfg.default -ne "9090") {
   $cfg.default = "9090"
}

######################
# Windows Management #
######################
# set permanent enviroment variable
[Environment]::SetEnvironmentVariable("vsprojects", "c:\projects", "User")
# go to directory set in enviroment variable "vsprojects"
cd ${env:vsprojects}

###########
# Profile #
###########
# create powershell profile in correct location
$powershelldir=”$env:personal\WindowsPowerShell”
if ( (test-path $powershelldir) -ne $true) {mkdir $powershelldir}
new-item $powershelldir\profile.ps1 -ItemType file
notepad $powershelldir\profile.ps1
