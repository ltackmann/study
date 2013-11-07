using WebAPIDemo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Http;

namespace WebAPIDemo
{
	public class UsersController : ApiController
	{
		User[] users = new User[] 
		{ 
			new User { Id = 1, FullName = "Steve Jobs", Username = "sjobs" }, 
			new User { Id = 2, FullName = "Bill Gates", Username = "bgates" }, 
			new User { Id = 3, FullName = "Martin Fowler", Username = "mfowler" } 
		};

		[HttpGet("users")]
		public IEnumerable<User> GetAllUsers()
		{
			return users;
		}

		[HttpGet("users/{userId}")]
		public User GetUserById(int userId)
		{
			var user = users.FirstOrDefault((p) => p.Id == userId);
			if (user == null)
			{
				throw new HttpResponseException(HttpStatusCode.NotFound);
			}
			return user;
		}
	}
}

