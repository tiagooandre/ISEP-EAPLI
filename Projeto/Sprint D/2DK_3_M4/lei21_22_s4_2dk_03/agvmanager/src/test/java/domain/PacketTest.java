package domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PacketTest {
    @Test
    public void testConstructor(){
        byte version = 0;
        byte code = ProtocolCodes.SETUP.getCodeKey();
        byte[] data = "Hello World".getBytes();

        Packet packet = new Packet(version, code, data);

        assertEquals(packet.getVersion(), version);
        assertEquals(packet.getCode(), code);
        assertEquals(packet.getData(), data);
    }
}