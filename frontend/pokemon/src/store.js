import { createStore } from 'redux';

const initialState = {
  'supportCategory': null,
  'contactEmail': '',
  'supportTitle': '',
  'supportContent': '',
};

function reducer(initialState) {
  console.log(initialState);
}
console.log(initialState);

export default createStore(reducer);
