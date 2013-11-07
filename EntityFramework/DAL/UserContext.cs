using EntityFrameworkDemo.Models;
using System.Data.Entity;

namespace EntityFrameworkDemo
{
	public class UserContext : DbContext
	{
		public DbSet<Employee> Employees { get; set; }

		protected override void OnModelCreating(DbModelBuilder modelBuilder)
		{
			// don't pluralize table names modelBuilder.Conventions.Remove<PluralizingTableNameConvention>();
		}
	}
}
