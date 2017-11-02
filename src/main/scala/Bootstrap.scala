import javax.servlet._
import skinny.micro._
import App

class Bootstrap extends LifeCycle {
  override def init(ctx: ServletContext) {
    App.mount(ctx)
  }
}
