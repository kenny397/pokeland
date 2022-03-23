import { routers } from "./Router";
import { updateHeaderDisplay } from "./redux/actions";
import store from "./redux/store";

export function decideHeaderDisplay(to) {
  const isNotIntro = to !=='/';
  const isNotTutorial = to !=='/tutorial';
  let headerDisplay = false;

  console.log(headerDisplay);

  for (let { path } of routers){
    if(to === path && isNotIntro && isNotTutorial){
      headerDisplay = true;
      break;
    }
  }
  return headerDisplay;
}

// export function changeHeaderDisplay(headerDisplay) {
//   store.dispatch(updateHeaderDisplay(headerDisplay));
// }

export function changeHeaderDisplay(to) {
  store.dispatch(updateHeaderDisplay((decideHeaderDisplay(to))));
}
