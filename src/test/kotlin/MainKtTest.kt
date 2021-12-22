import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calcCommission_Type1NoCommission() {
        val type = TYPE_1;
        val amountOfPreviousPurchases = 400_00
        val amountTransfer = 100_00
        val expectedResult = 0

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type1Commission() {
        val type = TYPE_1;
        val amountOfPreviousPurchases = 100_000_00
        val amountTransfer = 50_00
        val expectedResult = 23_00

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type1Zero() {
        val type = TYPE_1;
        val amountOfPreviousPurchases = 0
        val amountTransfer = 0
        val expectedResult = 20_00

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type2Zero() {
        val type = TYPE_2;
        val amountOfPreviousPurchases = 0
        val amountTransfer = 0
        val expectedResult = 20_00

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type2NoCommission() {
        val type = TYPE_2;
        val amountOfPreviousPurchases = 400_00
        val amountTransfer = 100_00
        val expectedResult = 0

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type2Commission() {
        val type = TYPE_2;
        val amountOfPreviousPurchases = 100_000_00
        val amountTransfer = 50_00
        val expectedResult = 23_00

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type3FixCommission() {
        val type = TYPE_3;
        val amountOfPreviousPurchases = 0
        val amountTransfer = 100_00
        val expectedResult = 35_00

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type3CommissionPercentage() {
        val type = TYPE_3;
        val amountOfPreviousPurchases = 100_000_00
        val amountTransfer = 5000_00
        val expectedResult = 37_50

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type4FixCommission() {
        val type = TYPE_4;
        val amountOfPreviousPurchases = 0
        val amountTransfer = 100_00
        val expectedResult = 35_00

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type4CommissionPercentage() {
        val type = TYPE_4;
        val amountOfPreviousPurchases = 100_000_00
        val amountTransfer = 5000_00
        val expectedResult = 37_50

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_Type5CommissionPercentage() {
        val type = TYPE_5;
        val amountOfPreviousPurchases = 100_000_00
        val amountTransfer = 5000_00
        val expectedResult = 0

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_InCorrectType() {
        val type = "test";
        val amountOfPreviousPurchases = 100_000_00
        val amountTransfer = 5000_00
        val expectedResult = 0

        val result = calcCommission(type, amountOfPreviousPurchases, amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_TypeDefaultAmountOfPreviousPurchases() {
        val type = TYPE_1;
        val amountTransfer = 100_00
        val expectedResult = 0

        val result = calcCommission(type = type, amountTransfer = amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun calcCommission_DefaultType() {
        val amountOfPreviousPurchases = 100_000_00
        val amountTransfer = 100_00
        val expectedResult = 0

        val result = calcCommission(amountOfPreviousPurchases = amountOfPreviousPurchases, amountTransfer = amountTransfer)

        assertEquals(expectedResult, result)
    }

    @Test
    fun convertToRubKop_Test() {
        val amount = 104_53
        val expectedResult = "104 руб 53 коп"

        val result = convertToRubKop(amount)

        assertEquals(expectedResult, result)
    }

}