import nfp from "../fixtures/nfp";

const initialState = {
  headerDisplay : false,
  nfps: {
    "1": [nfp],
  },
};

export default function reducer(state = initialState, action) {
  if (action.type === 'updateHeaderDisplay') {
    return {
      ...state,
      // TODO : 바뀐값 넣기
      headerDisplay: action.payload.headerDisplay
    };
  }

  return state;
}
