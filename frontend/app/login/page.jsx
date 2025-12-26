import LoginForm from "../components/LoginForm";

const Login = () => {
  return (
    <div className="mt-5">
      <div className="flex flex-col gap-2">
        <p className="text-center text-xl">Login Account</p>
        <hr className="border-1.5" />
      </div>
      
      <div className="flex justify-center">
        <LoginForm />
      </div>
    </div>
  )
}

export default Login;