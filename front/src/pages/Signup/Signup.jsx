import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const SInputLayout = css`
    margin-bottom: 15px;
    width: 60%;
    height: 40px;

    & > input {
        width: 100%;
        height: 100%;
        text-align: center;
    }
`;

function Signup(props) {

    const navigate = useNavigate();     //페이지 이동을 위함

    const [ signupUser, setSignupUser ] = useState({
        username: "",
        password: "",
        name: "",
        email: "",
    });

    const handleInputChage = (e) => {
        setSignupUser({
            ...signupUser,
            [e.target.name]: e.target.value
        })
    }

    const handleSubmitClick = () => {
        //회원가입 요청
        const option = {
            params: {
                username: signupUser.username
            }
        }
        axios.get("http://localhost:8080/servlet_study_jeongmin/auth/signup/duplicate/username", option) //get이 먼저 실행되고
        .then((response) => { //서버로부터 응답이 오면 then이 실행됨
            // console.log(response);
            axios.post("http://localhost:8080/servlet_study_jeongmin/auth/signup", signupUser)
            .then((response)=> {
                alert(response.data);   //응답으로 넘겨준 data가 들어있음
                navigate("/signin");    //회원가입이 성공하면 signin 페이지로 이동
            })

        }).catch((error) => {
            alert("중복된 아이디입니다.");
        })
    }

    return (
        <>
            <h1>회원가입</h1>
            <div css={SInputLayout}>
                <input type="text" name='username' placeholder='username' onChange={handleInputChage}/>
            </div>
            <div css={SInputLayout}>
                <input type="text" name='password' placeholder='password' onChange={handleInputChage}/>
            </div>
            <div css={SInputLayout}>
                <input type="text" name='name' placeholder='name' onChange={handleInputChage}/>
            </div>
            <div css={SInputLayout}>
                <input type="text" name='email' placeholder='email' onChange={handleInputChage}/>
            </div>
            <div>
                <button onClick={handleSubmitClick}>가입하기</button>
            </div>

        </>
    );
}

export default Signup;