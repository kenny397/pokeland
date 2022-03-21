import React from "react";

export default function SupportPage() {

  const onClickSubmit = function () {
    // TODO : 제출 버튼 클릭시 input값 내용을 모두 console에 출력
  };
  
  const onChangeSelectCategory = (e) => {
    console.log(e.target.value);
  };

  const onChangeEmail = (e) => {
    console.log(e.target.value);
  };

  const onChangeTitle = (e) => {
    console.log(e.target.value);
  };

  const onChangeContent = (e) => {
    console.log(e.target.value);
  };
  
  return (
    <div>
      <h1>고객센터</h1>
      <p>개발자에게 서비스 피드백을 보내주시면 500SSF를 드립니다!</p>
      <p>카테고리</p>
      <select name="categories" id="category-select" onChange={e => onChangeSelectCategory(e)}>
        <option value="">--카테고리를 선택해주세요--</option>
        <option value="review">리뷰</option>
        <option value="suggestion">건의사항</option>
        <option value="question">문의사항</option>
      </select>
      
      <p>답변 받을 이메일</p>
      <input type="text" name="" id="" onChange={e => onChangeEmail(e)} placeholder="이메일을 입력해주세요" />

      <p>제목</p>
      <input type="text" name="" id="title" placeholder="문의 제목을 입력해주세요" onChange={(e) => onChangeTitle(e)} />
      
      <p>내용 입력</p>
      <p>아래 내용을 기입해주세요</p>
      <textarea name="" id="" cols="30" rows="10" onChange={(e) => onChangeContent(e)} >
        
      </textarea>
      <button>취소</button>
      <button onClick={onClickSubmit}>제출</button>
    </div>
  );
}
