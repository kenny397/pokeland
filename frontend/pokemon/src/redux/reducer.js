const initialState = {
  showHeader : false,
};

export default function reducer(state = initialState, action) {
  if (action.type === 'updateShowHeader') {
    return {
      ...state,
      // TODO : 바뀐값 넣기
      showHeader: action.payload.showHeader
    };
  }

  return state;
}
