import React, { useState } from 'react'
import axios from "axios"
import { error } from 'console';
import Box from '@mui/material/Box';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Card from '@mui/material/Card';
import TextField from '@mui/material/TextField';
import { Button, Typography } from '@mui/material';
import { signUpApi } from '../../../apis';

interface Props{
  setAuthView: (authView : boolean) => void;
}

export default function SignUp(props: Props) {
  const [userEmail, setUserEmail] = useState<String>('');
  const [userPassword, setUserPassword] = useState<String>('');
  const [userPasswordCheck, setUserPasswordCheck] = useState<String>('');
  const [userNickname, setUserNickname] = useState<String>('');
  const [userPhoneNumber, setUserPhoneNumber] = useState<String>('');
  const [userAddress, setUserAddress] = useState<String>('');
  const [userAddressDetail, setUserAddressDetail] = useState<String>('');

  const { setAuthView } = props

  const signUpHandler = async () => {

    const data = {
      userEmail,
      userPassword,
      userPasswordCheck,
      userNickname,
      userPhoneNumber,
      userAddress,
      userAddressDetail

    };

    const signUpResponse = await signUpApi(data);
    if(!signUpResponse) {
      alert('회원가입에 실패했습니다.');
    return;
    }
    if(!signUpResponse.result){
      alert('회원가입에 실패했습니다.');
    }
    alert('회원가입에 성공했습니다.');
    setAuthView(false);

    
  };

  return (
    <Card sx={{ minWidth: 275, maxWidth: "50vw", padding: 5 }}>
      <Box>
        <Typography variant='h5'>회원가입</Typography>
      </Box>

      <Box height={'50vh'}>
        <TextField fullWidth label="이메일 주소" type="email" variant="standard" onChange={(e) => setUserEmail(e.target.value)} />
        <TextField fullWidth label="비밀번호" type="password" variant="standard" onChange={(e) => setUserPassword(e.target.value)} />
        <TextField fullWidth label="비밀번호 확인" type="password" variant="standard" onChange={(e) => setUserPasswordCheck(e.target.value)} />
        <TextField fullWidth label="닉네임" variant="standard" onChange={(e) => setUserNickname(e.target.value)} />
        <TextField fullWidth label="휴대폰 번호" variant="standard" onChange={(e) => setUserPhoneNumber(e.target.value)} />
        <TextField fullWidth label="주소" variant="standard" onChange={(e) => setUserAddress(e.target.value)} />
        <TextField fullWidth label="상세주소" variant="standard" onChange={(e) => setUserAddressDetail(e.target.value)} />

      </Box>

      <Box component='div'>
        <Button fullWidth onClick={() => signUpHandler()} variant="contained">회원가입
        </Button>
      </Box>
      <Box component='div' display='flex' mt={2}>
        <Typography>이미 계정이 있으신가요?</Typography>
        <Typography fontWeight={800} ml={1} onClick={() => setAuthView(false)}>로그인
        </Typography>
      </Box>



    </Card>
  );
}
