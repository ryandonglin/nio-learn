package xyz.dlin.thrift.demo.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import xyz.dlin.thrift.demo.service.Hello;
import xyz.dlin.thrift.demo.service.HelloServiceImpl;

/**
 * ${DESCRIPTION}
 *
 * @author dongl50@ziroom.com
 * @version 1.0.0
 * @date 2018/1/23
 * @since 1.0.0
 */
public class ThriftServerDemo {

    public static void main(String[] args) throws TTransportException {

        TServerSocket socket = new TServerSocket(7911);

        TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();

        TProcessor processor = new Hello.Processor(new HelloServiceImpl());

        TThreadPoolServer.Args args1 = new TThreadPoolServer.Args(socket);
        args1.processor(processor);
        args1.protocolFactory(proFactory);

        TServer server = new TThreadPoolServer(args1);

        System.out.println("Starting thrift server on port 7911");

        server.serve();

    }
}
