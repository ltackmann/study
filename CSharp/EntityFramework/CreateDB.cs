using System;
using System.Data;
using Mono.Data;
using Mono.Data.Sqlite;

namespace EntityFrameworkDemo
{
	// TODO https://code.google.com/p/ndbunit/wiki/QuickStartGuide
	public class CreateDB
	{
		public static void Main(string[] args)
		{
			string connectionString = "URI=file:SqliteTest.db";
			var connection = (IDbConnection) new SqliteConnection(connectionString);
			connection.Open();

			CreateTables (connection);
			// TODO insert data
			TestTables (connection);
		
			connection.Close();
			connection = null;
		}

		public static void CreateTables(IDbConnection connection) 
		{
			var command = connection.CreateCommand();
			command.CommandText = @"
			CREATE TABLE employee (
				firstname varchar(32),
				lastname varchar(32)
			)";
			command.ExecuteNonQuery ();
			command.Dispose();
			command = null;
		}

		public static void TestTables(IDbConnection connection) 
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
				Console.WriteLine("Name: " +
				                  FirstName + " " + LastName);
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

