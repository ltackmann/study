using System.ComponentModel.DataAnnotations.Schema;

namespace EntityFrameworkDemo.Models
{
	//[Table("EMPLOYEE")]
	public class Employee
	{
		//[Column("BlogDescription")]
		public string LastName { get; set; }
		public string FirstName { get; set; }
	}
}
