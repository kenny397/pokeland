const range = (start, end) => {
  let array = [];
  for (let i = start; i < end; ++i) {
    array.push(i);
  }
  return array;
};
// [1, 2, 3, 4, 5, 6, 7, 8, 9]

export default range;
