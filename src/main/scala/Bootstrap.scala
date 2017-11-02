import javax.servlet._
import skinny.micro._

class Bootstrap extends LifeCycle {
  override def init(ctx: ServletContext) {
    App.mount(ctx)
  }
}
