package xyz.dlin.thrift.demo.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import xyz.dlin.thrift.demo.service.Hello;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2018/1/24
 * @since 1.0.0
 */
public class ThriftClientDemo {

    public static void main(String[] args) {
        TTransport transport = new TSocket("localhost", 7911);
        try {
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);
            for (int i = 1; i <= 10; i++) {
                client.helloVoid();
            }
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
