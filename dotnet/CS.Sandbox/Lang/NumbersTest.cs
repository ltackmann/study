using NUnit.Framework;

namespace CS.Sandbox.Lang
{
    [TestFixture]
    class NumberTest
    {
        [Test]
        public void TestNumberRanges()
        {
            // integral types 
            sbyte signed_8bit_int = 0;    // -128 to 127
            byte unsigned_8bit_int = 0;    // 0 to 256
            short signed_16bit_int = 0;    // -32,768 to 32,767
            ushort unsigned_16bit_int = 0;    // 0 to 65,535
            int signed_32bit_int = 0;    // -2,147,483,648 to 2,147,483,647
            uint unsigned_32bit_int = 0;    // 0 to 4,294,967,295
            long signed_64bit_int = 0;    // -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807
            ulong unsigned_64bit_int = 0;    // 0 to 18,446,744,073,709,551,615

            // floating point types
            float signed_32bit_float = 0.0f;
            double signed_64bit_float = 0.0;
            decimal signed_128bit_monetary = 0.0M;
        }
    }
}
