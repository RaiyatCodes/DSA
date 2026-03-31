import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class BitInputStream{
    private ObjectInputStream stream;
    private int buffer;
    private int lastBits;
    private int nextByte;
    private boolean hasNextByte = false;
    private boolean readBitCalled = false;
    private int nextBit = 8;
    
    public BitInputStream(InputStream in) throws Exception{
        stream  = new ObjectInputStream(in);
    }
    
    public BitInputStream(String name) throws IOException{
        stream  = new ObjectInputStream(new FileInputStream(name));
    }
    
    synchronized public boolean hasNext(){
        if(stream == null){
            return false;
        }
        else if (!readBitCalled){
            try{
                buffer = stream.readUnsignedByte();
            }
            catch (IOException e){
                return false;
            }
            try{
                lastBits = stream.readUnsignedByte();
            }
            catch (IOException e){
                return false;
            }
            try{
                nextByte = stream.readUnsignedByte();
                nextBit = 8;
                hasNextByte = true;
            }
            catch (IOException e){
                nextBit = lastBits;
                lastBits = 0;
                hasNextByte = false;
            }
            readBitCalled = true;
            return true;
        }
        else if(nextBit == 0){
            if(!hasNextByte){
                return false;
            }
            else{
                buffer = lastBits;
                lastBits = nextByte;
                try{
                    nextByte = stream.readUnsignedByte();
                    nextBit = 8;
                    hasNextByte = true;
                }
                catch (IOException e){
                    nextBit = lastBits;
                    hasNextByte = false;
                }
                return true;
            }
        }
        else{
            return true;
        }
    }
    
    synchronized public int readBit() throws Exception{
        if(!hasNext()){
            throw new IOException("no bits left in the stream");
        }
        else{
            nextBit--;
            int bit = buffer & (1 << nextBit);
            bit = (bit == 0) ? 0 : 1;
            return bit;
        }
    }
    
    synchronized public Object readObject() throws IOException, ClassNotFoundException{
        if(stream == null){
            throw new IOException("this stream is not open for reading");
        }
        else if (readBitCalled){
            throw new IOException("cannot call readObject after a call to readBit");
        }
        return stream.readObject();
    }
    
    public void close() throws IOException{
        stream.close();
        stream = null;
    }
}
