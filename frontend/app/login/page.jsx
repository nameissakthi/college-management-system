import LoginForm from "../ui/components/LoginForm";

const Login = () => {
  return (
    <div className="mt-5">
      <div className="flex flex-col gap-2">
        <p className="text-center text-2xl">Login Account</p>
        <hr className="border-1.5" />
      </div>
      
      <div>
        <LoginForm />
      </div>
    </div>
  )
}

export default Login;