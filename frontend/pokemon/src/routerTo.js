import { routers } from "./Router";
import { updateShowHeader } from "./redux/actions";
import store from "./redux/store";

export function routerTo(to) {
  const isNotIntro = to !=='/';
  const isNotTutorial = to !=='/tutorial';
  let showHeader = false;
  console.log(showHeader);

  for (let { path } of routers){
    if(to === path && isNotIntro && isNotTutorial){
      showHeader = true;
      break;
    }
  }

  store.dispatch(updateShowHeader(showHeader));
}
