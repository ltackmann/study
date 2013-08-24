using System;
using System.Data;
using Mono.Data;
using Mono.Data.Sqlite;

namespace Database
{
	public class TestDatabase
	{
		public static void Main(string[] args)
		{
			var databaseTester = new TestDatabase ();
			databaseTester.RunTests ();
		}

		public void RunTests()
		{
			string connectionString = "URI=file:DatabaseTest.db";
			var connection = (IDbConnection) new SqliteConnection(connectionString);
			connection.Open();

			CreateDatabase (connection);
			InsertData (connection);
			QueryData (connection);

			connection.Close();
			connection = null;
		}

		private void CreateDatabase(IDbConnection connection)
		{
			var command = connection.CreateCommand();
			command.CommandText = @"
			DROP TABLE IF EXISTS employee;
			CREATE TABLE employee (
				firstname varchar(32),
				lastname varchar(32)
			)";
			command.ExecuteNonQuery ();
			command.Dispose();
			command = null;
		}

		private void InsertData(IDbConnection connection)
		{
			// TODO switch to NdbUnit https://code.google.com/p/ndbunit/wiki/QuickStartGuide
			var command = connection.CreateCommand();
			command.CommandText = @"
			INSERT INTO
				employee
			VALUES 
				('Lars', 'Tackmann')";
			command.ExecuteNonQuery ();
			command.Dispose();
			command = null;
		}

		private void QueryData(IDbConnection connection)
		{
			var command = connection.CreateCommand();
			command.CommandText = @"
			SELECT
				firstname, lastname
			FROM
				employee
			";

			var reader = command.ExecuteReader();
			while(reader.Read()) {
				string FirstName = reader.GetString (0);
				string LastName = reader.GetString (1);
				Console.WriteLine("Name: " + FirstName + " " + LastName);
			}
			// clean up
			// TODO switch to USING
			reader.Close();
			reader = null;
			command.Dispose();
			command = null;
		}
	}
}

