import BusAxios from "../apis/BusAxios"

export const login = async function (username, password){
    const data = {
        username: username,
        password: password
    }
    try{
        const ret = await BusAxios.post('users/auth', data);
        window.localStorage.setItem('jwt', ret.data);
    }catch(error){
        console.log(error);
    }
    window.location.assign("/");
}
export const logout = function(){
    window.localStorage.removeItem('jwt');
    window.location.assign("/");
}