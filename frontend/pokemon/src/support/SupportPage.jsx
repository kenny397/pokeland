import React from "react";

// antd
import { Button, Input, Select } from "antd";
const { TextArea } = Input;
const { Option } = Select;

export default function SupportPage() {
  // 이건 보고나서 지우겠습니다.
  // const { category, email, title, content } = useSelector((state) => ({
  //   category: state.supportCategory,
  //   email: state.contactEmail,
  //   title: state.supportTitle,
  //   content: state.supportContent,
  // }));

  // const dispatch = useDispatch();

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
      <h1>고객센터</h1>
      <p>개발자에게 서비스 피드백을 보내주시면 500SSF를 드립니다!</p>
      <p>카테고리</p>
      <Select name="categories" id="category-select" defaultValue="defaultOption" style={{ width: 120 }} onChange={(e) => onChangeSelectCategory(e)}>
        <Option value="defaultOption">--카테고리를 선택해주세요--</Option>
        <Option value="review">리뷰</Option>
        <Option value="suggestion">건의사항</Option>
        <Option value="question">문의사항</Option>
      </Select>
      
      <p>답변 받을 이메일</p>
      <Input type="text" name="" id="" onChange={e => onChangeEmail(e)} placeholder="이메일을 입력해주세요" />

      <p>제목</p>
      <Input type="text" name="" id="title" placeholder="문의 제목을 입력해주세요" onChange={(e) => onChangeTitle(e)} />
      
      <p>내용 입력</p>
      <p>아래 내용을 기입해주세요</p>
      <TextArea name="" id="" cols="30" rows="10" placeholder="내용을 입력해주세요" onChange={(e) => onChangeContent(e)} >
        
      </TextArea>
      <Button>취소</Button>
      <Button onClick={onClickSubmitSupport}>제출</Button>
    </div>
  );
}
