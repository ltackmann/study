using System;
using System.Data;
using NUnit.Framework;

namespace CS.Sandbox.Db
{
	[TestFixture]
	public class SQLiteFileTest
	{
		[Test]
		public void RunTests()
		{
            var connectionString = "URI=file:DatabaseTest.db";
            using (var connection = new SQLiteConnection(connectionString))
            {
                connection.Open();

                CreateDatabase(connection);
                InsertData(connection);
                QueryData(connection);
            }
		}

		private void CreateDatabase(IDbConnection connection)
		{
            using (var command = connection.CreateCommand())
            {
                command.CommandText = @"
			    DROP TABLE IF EXISTS employee;
			    CREATE TABLE employee (
				    firstname varchar(32),
				    lastname varchar(32)
			    )";
                command.ExecuteNonQuery();
            }
		}

		private void InsertData(IDbConnection connection)
		{
            using (var command = connection.CreateCommand())
            {
                command.CommandText = @"
			    INSERT INTO
				    employee
			    VALUES 
				    ('Lars', 'Tackmann')";
                command.ExecuteNonQuery();
            }
		}

		private void QueryData(IDbConnection connection)
		{
            using (var command = connection.CreateCommand())
            {
                command.CommandText = @"
			    SELECT
				    firstname, lastname
			    FROM
				    employee
			    ";

                using (var reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        string FirstName = reader.GetString(0);
                        string LastName = reader.GetString(1);
                        Console.WriteLine("Name is: " + FirstName + " " + LastName);
                    }
                }
            }
		}
	}
}

