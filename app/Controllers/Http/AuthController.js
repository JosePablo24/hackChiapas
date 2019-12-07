'use strict'

const User = use('App/Models/User');

class AuthController {
    async register({request, auth, response}) {
        
        const username = request.input("username")
        const email = request.input("email")
        const password = request.input("password")
        const name = request.input("name")
        const lastname = request.input("lastname")
        const phone = request.input("phone")
        const genre = request.input("genre")
        

        let user = new User()
        user.username = username
        user.email = email
        user.password = password
        user.name = name
        user.lastname = lastname
        user.phone = phone
        user.genre = genre
        


        await user.save()
        let accessToken = await auth.generate(user)
        return response.json({"user": user, "access_token": accessToken})
    }

    async login({request, auth, response}) {
        const username = request.input("username")
        const password = request.input("password");
        try {
          if (await auth.attempt(username, password)) {
            let user = await User.findBy('username', email)
            let accessToken = await auth.generate(user)
            return response.json({"user":user, "access_token": accessToken})
          }

        }
        catch (e) {
          if(e.name == 'UserNotFoundException'){
            return response.json({message: 'You first need to register!'})
          }else{
            return response.json({message: 'invalid password'})
          }
          console.log(e)
          
        }
    }
    
    async destroy({ params, request, response }) {
        let { id } = params
        let user = await User.findOrFail(id)

        await user.delete()

        return response.status(200).json({
            msg: 'Eliminado'
        })
    }
}

module.exports = AuthController
