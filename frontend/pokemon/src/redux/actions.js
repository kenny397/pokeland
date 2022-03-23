export function updateHeaderDisplay(headerDisplay) {
  return {
    type: 'updateHeaderDisplay',
    payload: {
      headerDisplay,
    },
  };
}
