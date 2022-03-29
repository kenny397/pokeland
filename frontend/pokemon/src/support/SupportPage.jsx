import React, { useState } from "react";
import { Link } from "react-router-dom";

import './SupportPage.scss';

export default function SupportPage() {
  const [inputs, setInputs] = useState({
    category: '',
    email: '',
    title: '',
    content: ''
  });
  
  const onClickSubmitSupport = function () {
    // TODO : 버튼 클릭시 백엔드로 비동기 요청 
    console.log(inputs);
  };

  const onChangeInputs = (e) => {
    const { value, name } = e.target;
    setInputs({
      ...inputs,
      [name]: value
    });
    
  };
  
  return (
    <div className="SupportPage">
      <h1 className="header">고객센터</h1>
      <div className="sub-title">
        <span >개발자에게 서비스 피드백을 보내주시면 </span><span className="token">500SSF</span><span>를 드립니다!</span>
      </div>
      <div className="form-item">
        <label htmlFor="category-select" className="form-item-title">카테고리</label><span className="asterisk">*</span><br />
        <select name="category" id="category-select" onChange={(e) => onChangeInputs(e)} defaultValue="">
          <option value="" disabled>카테고리 선택</option>
          <option value="review" >리뷰</option>
          <option value="suggestion">건의사항</option>
          <option value="question">문의사항</option>
        </select>
      </div>
      
      <div className="form-item">
        <label htmlFor="support-email" className="form-item-title">답변 받을 이메일</label><span className="asterisk">*</span><br />
        <input type="text" name="email" id="support-email" onChange={e => onChangeInputs(e)} placeholder="이메일을 입력해주세요" />
      </div>

      <div className="form-item">
        <label htmlFor="support-title" className="form-item-title">제목</label><span className="asterisk">*</span>
        <input type="text" name="title" id="support-title" placeholder="문의 제목을 입력해주세요" onChange={(e) => onChangeInputs(e)} />
      </div>
  
      <div className="form-item">
        <label className="form-item-title">내용 입력</label><span className="asterisk">*</span>
        <p>아래 내용을 기입해주세요</p>
        <textarea name="content" className="feedback-input" id="" cols="30" rows="10" placeholder="내용을 입력해주세요" onChange={(e) => onChangeInputs(e)} >
        </textarea>
      </div>
      
      <div className="buttons">
        <button className="cancel-button"><Link to="/main">취소</Link></button>
        <button className="submit-button" onClick={onClickSubmitSupport}>제출</button>
      </div>

    </div>
  );
}
