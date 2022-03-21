module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: [
  ],
  parserOptions: {
    ecmaFeatures: {
      jsx: true,
    },
    ecmaVersion: 'latest',
    sourceType: 'module',
  },
  plugins: [
    'react',
  ],
  rules: {
    'linebreak-style': 0,
    'semi': ['error', 'always'], // 세미콜론
    'react/prop-types': 'off', // prop으로 오는 arg 타입 검사해서 오류 뿜는것 무시
    'indent': ['error', 2, { 'StaticBlock': { 'body': 1 } }], // indent 2spaces and object indent 한 번으로
    'object-curly-spacing': ['error', 'always'], // {} 안 1space
    'eol-last': ['error', 'always'], // 파일 막줄 공백 라인
    'no-multiple-empty-lines': ['error', { 'max': 1, 'maxEOF': 0, 'maxBOF': 0 }], // 공백라인 최대 1줄로
  },
};
