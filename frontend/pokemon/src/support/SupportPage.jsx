import React from "react";
import './SupportPage.css';
// import { decideHeaderDisplay, changeHeaderDisplay } from '../headerDisplay';
// import { useNavigate } from "react-router-dom";

export default function SupportPage() {
  
  let category, email, title, content;
  
  const onClickSubmitSupport = function () {
    // TODO : 버튼 클릭시 백엔드로 비동기 요청 
    console.log(category);
    console.log(email);
    console.log(title);
    console.log(content);
  };
  
  const onChangeSelectCategory = (e) => {
    category = e;
  };

  const onChangeEmail = (e) => {
    // dispatch(updateSupportEmail(e.target.value));
    email = e.target.value;
  };

  const onChangeTitle = (e) => {
    title = e.target.value;
  };

  const onChangeContent = (e) => {
    content = e.target.value;
  };
  
  return (
    <div>
      <h1 className="header">고객센터</h1>
      <div className="sub-title">
        <span >개발자에게 서비스 피드백을 보내주시면 </span><span className="token">500SSF</span><span>를 드립니다!</span>
      </div>
      <div className="form-item">
        <label htmlFor="category-select" className="form-item-title">카테고리</label><span className="asterisk">*</span><br />
        <select name="categories" id="category-select" defaultValue="review" onChange={(e) => onChangeSelectCategory(e)}>
          <option value="review">리뷰</option>
          <option value="suggestion">건의사항</option>
          <option value="question">문의사항</option>
        </select>
      </div>
      
      <div className="form-item">
        <label htmlFor="support-email" className="form-item-title">답변 받을 이메일</label><span className="asterisk">*</span><br />
        <input type="text" name="" id="support-email" onChange={e => onChangeEmail(e)} placeholder="이메일을 입력해주세요" />
      </div>

      <div className="form-item">
        <label htmlFor="support-title" className="form-item-title">제목</label><span className="asterisk">*</span>
        <input type="text" name="" id="support-title" placeholder="문의 제목을 입력해주세요" onChange={(e) => onChangeTitle(e)} />
      </div>
  
      <div className="form-item">
        <label className="form-item-title">내용 입력</label><span className="asterisk">*</span>
        <p>아래 내용을 기입해주세요</p>
        <textarea name="" className="feedback-input" id="" cols="30" rows="10" placeholder="내용을 입력해주세요" onChange={(e) => onChangeContent(e)} >
        </textarea>
      </div>
      
      <div className="buttons">
        <button className="cancel-button">취소</button>
        <button className="submit-button" onClick={onClickSubmitSupport}>제출</button>
      </div>

    </div>
  );
}
