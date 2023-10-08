using System;
using Nancy;

public class SampleModule : NancyModule
{
	// http://www.kristofclaes.be/blog/2011/08/23/hosting-nancy-from-a-console-application/
	public SampleModule()
	{
		Get["/view"] = parameters => {
			var person = new
			{
				Id = 1,
				Name = "Scott Hanselman",
				Content = "Lorem ipsum..."
			};
			return View["index", person];
		};

		
		Get["/person"] = parameters =>
		{
			var person = new
			{
				Id = 1,
				Name = "Scott Hanselman",
				Content = "Lorem ipsum..."
			};
			return Response.AsJson(person);
		};

		Get["/"] = x =>
		{
			return "Hello world!";
		};

	}
}