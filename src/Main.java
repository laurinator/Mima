public class Main {

    public static void main(String[] args) throws NumberSizeException {
        MimaMemory memory = new MimaMemory();
        memory.memSet(0x0, 0x9);
        System.out.println(memory.memRead(0x0));
        System.out.println(memory.toString(0x0, 0x10));
    }

}
