package sttp.tapir.server.netty

import sttp.client3.testing.SttpBackendStub
import sttp.monad.FutureMonad
import sttp.tapir.server.interceptor.CustomiseInterceptors
import sttp.tapir.server.tests.{CreateServerStubTest, ServerStubTest}

import java.net.InetSocketAddress
import scala.concurrent.{ExecutionContext, Future}

object NettyFutureCreateServerStubTest extends CreateServerStubTest[Future, NettyFutureServerOptions[InetSocketAddress]] {
  override def customiseInterceptors: CustomiseInterceptors[Future, NettyFutureServerOptions[InetSocketAddress]] =
    NettyFutureServerOptions.customiseInterceptors
  override def stub[R]: SttpBackendStub[Future, R] = SttpBackendStub(new FutureMonad()(ExecutionContext.global))
  override def asFuture[A]: Future[A] => Future[A] = identity
}

class NettyFutureServerStubTest extends ServerStubTest(NettyFutureCreateServerStubTest)
