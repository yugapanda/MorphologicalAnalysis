/**
  * Created by yuga on 2017/11/02.
  */
import javax.servlet._
import skinny.micro._
import app._

class Bootstrap extends LifeCycle {
  override def init(ctx: ServletContext) {
    app.mount(ctx)
  }
}