using System;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Sandbox.Lang
{
	class Animal { }
	class Cat: Animal { }
	
	public class VarianceTest
	{
		// To understand what the new CoVariance and ContraVariance code does for you
		// Try deleting or adding the words out and in from the following 2 lines of code:
		delegate T Func1<out T>();
		delegate void Action1<in T>(T a);

		static void Main(string[] args)
		{
			// Covariance
			Func1<Cat> cat = () => new Cat();
			Func1<Animal> animal = cat;

			// Contravariance
			Action1<Animal> act1 = (ani) => { Console.WriteLine(ani); };
			Action1<Cat> cat1 = act1;
		}        
	}
}

