import 'dart:convert';

import 'package:first_laboratory/animations.dart';
import 'package:first_laboratory/homepage.dart';
import 'package:first_laboratory/themes.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_facebook_login/flutter_facebook_login.dart';
import 'package:http/http.dart' as http;


void main() {
  ThemeBloc themeBloc = ThemeBloc();
  runApp(
    StreamBuilder<ThemeData>(
      initialData: themeBloc.initialTheme().data,
      stream: themeBloc.themeDataStream,
      builder: (BuildContext context, AsyncSnapshot<ThemeData> snapshot) {
        return MaterialApp(
          title: 'Share a meal',
          theme: snapshot.data,
          home: LoginPage(
            tBloc: themeBloc,
          ),
        );
      },
    )
  );
}


class LoginPage extends StatefulWidget {
  final ThemeBloc tBloc;
  LoginPage({Key key, this.tBloc}) : super(key: key);
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> with SingleTickerProviderStateMixin {

  AnimationController animationController;
  Animation<double> animation;
  Animation<double> sizeAnimation;
  bool isLoggedIn = false;
  
  var profileData;

  @override
  void initState() {
    super.initState();
    animationController = AnimationController(duration: Duration(milliseconds: 500),vsync: this);
    animation = Tween<double>(begin: 0,end: 60).animate(animationController)..addListener((){
      setState(() {
        
      });
    });
    sizeAnimation = Tween<double>(begin: 0,end: 1).animate(CurvedAnimation(parent: animationController,curve: Curves.fastOutSlowIn))..addListener((){
      setState(() {
        
      });
    });
  }

  var facebookLogin = FacebookLogin();

  void onLoginStatusChanged(bool isLoggedIn, {profileData}) {
    setState(() {
      this.isLoggedIn = isLoggedIn;
      this.profileData = profileData;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
        appBar: AppBar(
          backgroundColor: Color(0xffDBD2F4),
          title: Text("Facebook Login"),
          actions: <Widget>[
            IconButton(
              icon: Icon(
                Icons.exit_to_app,
                color: Colors.white,
              ),
              onPressed: () => facebookLogin.isLoggedIn
                  .then((isLoggedIn) => isLoggedIn ? _logout() : {}),
            ),
            
          ],
        ),
        body: Container(
          child: Center(
            child:Stack( 
              children: <Widget>[ 
              isLoggedIn
                ? _displayUserDataAndHomeButton(profileData, context)
                : _displayLoginButton()
                ],
                )
          ),
        ),
      );
  }

  void initiateFacebookLogin() async {
    var facebookLoginResult =
        await facebookLogin.logIn(['email']);

    switch (facebookLoginResult.status) {
      case FacebookLoginStatus.error:
        onLoginStatusChanged(false);
        break;
      case FacebookLoginStatus.cancelledByUser:
        onLoginStatusChanged(false);
        break;
      case FacebookLoginStatus.loggedIn:
        var graphResponse = await http.get(
            'https://graph.facebook.com/v2.12/me?fields=name,first_name,last_name,email,picture.height(200)&access_token=${facebookLoginResult
                .accessToken.token}');

        var profile = json.decode(graphResponse.body);
        print(profile.toString());

        onLoginStatusChanged(true, profileData: profile);
        break;
    }
  }

  _displayUserDataAndHomeButton(profileData, context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        Container(
          height: 200.0,
          width: 200.0,
          decoration: BoxDecoration(
            shape: BoxShape.circle,
            image: DecorationImage(
              fit: BoxFit.fill,
              image: NetworkImage(
                profileData['picture']['data']['url'],
              ),
            ),
          ),
        ),
        SizedBox(height: 28.0),
        Text(
          "Logged in as: ${profileData['name']}",
          style: TextStyle(
            fontSize: 20.0,
          ),
        ),
        SizedBox(height: 28.0,),
        CupertinoButton(
              onPressed: () => isLoggedIn
                        ? _changePage(context, profileData['name'], profileData['email'] ,NetworkImage(profileData['picture']['data']['url']))
                        : null,
              child: Text(
                "Go to list",
                style: TextStyle(color: Colors.black.withOpacity(0.6), fontSize: 24),
                ),
              color: Color(0xffDBD2F4),

            ),
      ],
    );
  }

  _displayLoginButton() {
    return RaisedButton(
      child: Text("Login with Facebook"),
      onPressed: () => initiateFacebookLogin(),
    );
  }

  _logout() async {
    await facebookLogin.logOut();
    onLoginStatusChanged(false);
    print("Logged out");
  }

  _changePage(BuildContext context, String name_, String email_, NetworkImage image_) {
    Navigator.of(context).push(new SlideTransitionRoute(builder: (context) =>
        new MyHomePage(title: 'Share a meal', name: name_, image: image_, email: email_, tBlock: widget.tBloc,
    )));
     //Navigator.push(context, new CupertinoPageRoute(
     //   builder: (context) => new  MyHomePage(title: "Share a meal", name : name_, image: image_, email: email_, tBlock: widget.tBloc)));
  }

}