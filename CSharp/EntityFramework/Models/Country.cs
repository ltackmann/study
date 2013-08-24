using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace EntityFrameworkDemo.Models
{
	[Table("COUNTRIES")]
	public class Country
	{
		[Key]
		public long Id { get; set; }

		[Required]
		[StringLength(2)] 
		public string Code { get; set; }

		[Required]
		public string Name { get; set; }
	}
}

