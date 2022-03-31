import { routers } from "./Router";
import { updateHeaderDisplay } from "./redux/actions";
import store from "./redux/store";

export function decideHeaderDisplay(to) {
  const getDomain = (to) => {
    let slashCnt = 0;
    let i = 0;
    let domain = '';
    while (i <= to.length) {
      if (slashCnt >= 2) {
        break;
      }
      if (to[i] === '/') {
        slashCnt += 1;
        if (slashCnt >= 2) {
          break;
        }
      }
      if (i != to.length) {
        domain = domain + to[i];
      }
      i++;
    }
    return domain;
  };

  const domain = getDomain(to);
  console.log(`domain is ${domain}`);

  const isNotIntro = to !=='/';
  const isNotTutorial = to !=='/tutorial';
  const isNotSignup = to !=='/signup';
  let headerDisplay = false;

  for (let { path } of routers){
    if(
      (path.includes(to) || path.includes(domain)) 
      && isNotIntro 
      && isNotTutorial 
      && isNotSignup
    ){
      headerDisplay = true;
      break;
    }
  }
  return headerDisplay;
}

export function changeHeaderDisplay(to) {
  store.dispatch(updateHeaderDisplay((decideHeaderDisplay(to))));
}
