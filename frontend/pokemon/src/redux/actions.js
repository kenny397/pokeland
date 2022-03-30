export function updateHeaderDisplay(headerDisplay) {
  return {
    type: 'updateHeaderDisplay',
    payload: {
      headerDisplay,
    },
  };
}

export function updateJwt(jwt) {
  return {
    type: 'updateJwt',
    payload: {
      jwt,
    },
  };
}
