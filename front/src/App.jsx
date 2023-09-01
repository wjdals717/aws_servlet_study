import logo from './logo.svg';
import './App.css';
import MainLayout from './components/MainLayout/MainLayout';
/** @jsxImportSource @emotion/react */
import { Global, css } from "@emotion/react";
import { Route, Routes } from 'react-router-dom';
import Signup from './pages/Signup/Signup';

const SCommon = css`
  * {
    box-sizing: border-box;
  }
`;

function App() {
  return (
    <>
      <Global styles={SCommon}/>

      <MainLayout>
        <Routes>
          <Route path='/'></Route>
          <Route path='/signin' element={<></>}></Route>
          <Route path='/signup' element={<Signup />}></Route>
        </Routes>
      </MainLayout>
    </>
  );
}

export default App;
