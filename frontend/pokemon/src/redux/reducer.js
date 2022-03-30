import nfp from "../fixtures/nfp";

const initialState = {
  headerDisplay : false,
  nfps: {
    "1": [nfp, nfp],
    "2": [nfp],
  },
  jwt: '',
};

export default function reducer(state = initialState, action) {
  if (action.type === 'updateHeaderDisplay') {
    return {
      ...state,
      // TODO : 바뀐값 넣기
      headerDisplay: action.payload.headerDisplay
    };
  }
  if (action.type === 'updateJwt'){
    return {
      ...state,
      jwt: action.payload.jwt
    };
  }

  return state;
}
