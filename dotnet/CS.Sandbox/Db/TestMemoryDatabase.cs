using System;
using System.Data;
using Mono.Data;
using Mono.Data.Sqlite;
using NUnit.Framework;

namespace Lang
{
	public class TestMemoryDatabase
	{
		public TestMemoryDatabase ()
		{
			var connection = new SQLiteConnection("Data Source=:memory:");
		}
	}
}

