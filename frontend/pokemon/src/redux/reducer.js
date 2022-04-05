import nfp from "../fixtures/nfp";

const initialState = {
  headerDisplay : false,
  existingPokemons: [],
  nfps: {
    "1": [nfp, nfp],
    "2": [nfp],
  },
  existingNfps: [],
  jwt: '',
  balance: '',
  isMusicMuted: true,
  gachaOrder: 0,
  supportOrder: 0,
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
  if (action.type === 'updateBalance') {
    return {
      ...state,
      balance: action.payload.balance
    };
  }
  if (action.type === 'setExistingPokemons') {
    return {
      ...state,
      existingPokemons: action.payload.existingPokemons
    };
  }
  if (action.type === 'setExistingNfps') {
    // console.log(`reducer is excuted `);
    return {
      ...state,
      existingNfps: action.payload.existingNfps
    };
  }
  if (action.type === 'setIsMusicMuted') {
    return {
      ...state,
      isMusicMuted: action.payload.isMusicMuted
    };
  }

  if (action.type === 'setGachaOrder') {
    return {
      ...state,
      gachaOrder: action.payload.gachaOrder
    };
  }

  if (action.type === 'setSupportOrder') {
    return {
      ...state,
      supportOrder: action.payload.supportOrder
    };
  }
  return state;
}
