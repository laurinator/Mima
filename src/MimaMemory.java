/**
 * Class to represent the memory of MIMA,
 * a basic representation of a computer with von Neumann architecture, invented at KIT.
 * @author Laurin Mayer
 * @version 1.0
 */
public class MimaMemory {

    private final int[] memory = new int[1048576];

    /**
     * Function to set the memory cell at address to value
     * @param address 32Bit Integer with 12 leading zeros, representing a 20 bit number.
     * @param value 32Bit Integer with 8 leading zeros, representing a 24 bit number.
     * @throws NumberSizeException if the described format of value and address are not correct.
     */
    public void memSet(int address, int value) throws NumberSizeException {

        if(checkNumberFormat(address, 20)){
            if(checkNumberFormat(value, 24)){
                memory[address] = value;
            } else {
                throw new NumberSizeException("Expected to get a number in the interval [0; 16777215] but got " + value);
            }
        } else {
            throw new NumberSizeException("Expected to get a number in the interval [0; 1048575] but got " + address);
        }

    }

    /**
     * Function to get the value in memory at address.
     * @param address 32Bit Integer with 12 leading zeros, representing a 20 bit number.
     * @return 32Bit Integer with 8 leading zeros, representing a 24 bit number, which is saved at index address in memory
     * @throws NumberSizeException if the described format of value and address are not correct.
     */
    public int memRead(int address) throws NumberSizeException {

        if(checkNumberFormat(address, 20)) {
            return memory[address];
        } else {
            throw new NumberSizeException("Expected to get a number in the interval [0; 1048575] but got " + address);
        }

    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        try {
            for (int index = 0; index < 1048576; index++) {

                result.append(fillWithLeadingZerosBinary(index, 20));
                result.append(" : ");
                result.append(fillWithLeadingZerosBinary(index, 24));
                result.append(System.lineSeparator());

            }
        } catch (NumberSizeException e) {
            //cannot be reached, values are always checked before they are written to the memory
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * Function to represent the memory as a String, but reverses the order of values.
     * @param reverse Reverse the list?
     * @return Reversed String representation of the memory.
     */
    public String toString(boolean reverse) {

        if(!reverse){
            return toString();
        }

        StringBuilder result = new StringBuilder();

        try {
            for (int index = 1048575; index >= 0; index--) {

                result.append(fillWithLeadingZerosBinary(index, 20));
                result.append(" : ");
                result.append(fillWithLeadingZerosBinary(memory[index], 24));
                result.append(System.lineSeparator());

            }
        } catch (NumberSizeException e) {
            //cannot be reached, values are always checked before they are written to the memory
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * Function to represent a section of the memory as a String.
     * @param startIndex 32Bit Integer with 12 leading zeros, representing a 20 bit number.
     * @param endIndex 32Bit Integer with 12 leading zeros, representing a 20 bit number.
     * @return String representation of the memory starting frmo startIndex and ending at endIndex.
     */
    public String toString(int startIndex, int endIndex) throws NumberSizeException {

        StringBuilder result = new StringBuilder();

        if(!checkNumberFormat(startIndex, 20) || !checkNumberFormat(endIndex, 20)){throw new NumberSizeException("");} //TODO: Make this print a nice message

        try {
            for (int index = startIndex; index < endIndex; index++) {

                result.append(fillWithLeadingZerosBinary(index, 20));
                result.append(" : ");
                result.append(fillWithLeadingZerosBinary(memory[index], 24));
                result.append(System.lineSeparator());

            }
        } catch (NumberSizeException e) {
            //cannot be reached, values are always checked before they are written to the memory
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * Appends leading zeros to the binary representation of a number.
     * @param number Number to format, smaller than 2^desiredLength.
     * @param desiredLength Length that number will be formatted to.
     * @return String representing number in binary of length desiredLength, filled with leading zeros.
     * @throws NumberSizeException if the binary representation of number is longer than desiredLength.
     */
    public static String fillWithLeadingZerosBinary(int number, int desiredLength) throws NumberSizeException {

        if(!checkNumberFormat(number, desiredLength)){
            throw new NumberSizeException("Expected to get a number in the interval [0; " + (int) (Math.pow(2, desiredLength) - 1) + "] but got " + number);
        }

        StringBuilder numberString = new StringBuilder(Integer.toBinaryString(number));
        int numbersToAdd = desiredLength - numberString.length();
        for (int i = 0; i < numbersToAdd; i++) {
            numberString.insert(0, "0");
        }

        return numberString.toString();

    }

    /**
     * Checks if a certain number can be represented as a size_bits size binary number.
     * @param number The number to check.
     * @param size_bits The size this number is supposed to have.
     * @return number < 2^size_bits
     */
    private static boolean checkNumberFormat(int number, int size_bits){

        return number < (int) Math.pow(2, size_bits);

    }

}
