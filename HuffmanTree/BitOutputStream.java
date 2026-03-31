import java.io.IOException;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.IllegalArgumentException;

public class BitOutputStream{
    private ObjectOutputStream stream;
    private int buffer = 0;
    private int bitCount = 8;
    private boolean writeBitCalled = false;
    
    public BitOutputStream(OutputStream out) throws IOException{
        stream = new ObjectOutputStream(out);
    }
    
    public BitOutputStream(String name) throws IOException{
        stream = new ObjectOutputStream(new FileOutputStream(name));
    }
    
    synchronized public void writeObject(Object obj) throws IOException{
        if(stream == null){
            throw new IOException("the stream is not open for writing");
        }
        else if (writeBitCalled){
            throw new IOException("cannot call writeObject after a call to writeBit");
        }
        stream.writeObject(obj);
    }
    
    synchronized public void writeBit(int bit) throws IOException{
        if(stream == null){
            throw new IOException("the stream is not open for writing");
        }
        if (bit != 0 && bit != 1){
            throw new IOException(bit + " is not a bit; did you use chars '0', '1'? use ints 0,1");
        }
        writeBitCalled = true;
        buffer  = (buffer << 1) | bit;
        bitCount--;
        if(bitCount == 0){
            flush();
        }
    }
    
    private void flush() throws IOException{
        if(bitCount == 0){
            stream.write((byte) buffer);
            bitCount = 8;
            buffer = 0;
        }
    }
    
    public void close() throws IOException{
        if(writeBitCalled){
            if(bitCount != 8){
                stream.write((byte) buffer);
            }
            else{
                bitCount = 0;
            }
            stream.write( (byte) (8-bitCount));
        }
        stream.close();
        stream = null;
    }
    
    
}

