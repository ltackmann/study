using System;
using System.Data;

namespace EntityFrameworkDemo
{
	// TODO switch to creating Database from model http://msdn.microsoft.com/en-us/data/jj193542.aspx 
	public class CreateDB
	{
		public static void Main (string[] args)
		{
			using (var context = new UserContext()) {
				var employees = context.Employees;

				foreach (var employee in employees) {
					Console.WriteLine ("Name: " + employee.FirstName + " " + employee.LastName);
				}
			}
		}
	}
}
