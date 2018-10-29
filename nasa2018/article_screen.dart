//import 'dart:convert';
//import 'package:flutter/material.dart';
//import 'package:nasa/Model/Model.dart';
//import 'dart:async';
//import 'package:http/http.dart' as http;
//import 'package:url_launcher/url_launcher.dart';
//

////String API_KEY = 'National';
//Future<List<Article>> fetchArticleBySource(String source) async{
//
//
//  final response =

////    await http.get('https://launchlibrary.net/1.4/agency?name==${API_KEY}');
//
//  if(response.statusCode == 200) //HTTP
//      {
//    List articles = json.decode(response.body)['articles'];
//    return articles.map((article) => new Article.fromJson(article)).toList();
//  }
//  else
//  {
//    throw Exception('Failed to load article list');
//
//  }
//}
//
//class  ArticleScreen extends StatefulWidget{
//
//  final Source source;
//
//  ArticleScreen({Key key, @required this.source}):super(key:key);
//
//  @override
//  State<StatefulWidget> createState() => ArticleScreenState();
//
//}
//
//class ArticleScreenState extends State<ArticleScreen>{
//
//  var list_articles;
//  var refreshKey = GlobalKey<RefreshIndicatorState>();
//
//  @override
//  void initState(){
//
//    refreshListArticle();
//
//  }
//
//  Future<Null> refreshListArticle() async{
//
//    refreshKey.currentState?.show(atTop: false);
//
//    setState(() {
//      list_articles = fetchArticleBySource(widget.source.id);
//    });
//
//    return null;
//
//  }
//
//  @override
//  Widget build(BuildContext context) {
//    return MaterialApp(
//
//      title: 'Nasa launch time',
//      theme: ThemeData(primarySwatch: Colors.blue),
//      home:  Scaffold(
//        appBar:  AppBar(title: Text(widget.source.name)),
//        body: Center(
//
//          child: RefreshIndicator( key: refreshKey,
//            child: FutureBuilder<List<Article>>(
//            future: list_articles,
//            builder: (context, snapshot){
//              if(snapshot.hasError){
//                return Text('${snapshot.error}');
//              }else if(snapshot.hasData){
//                List<Article> articles = snapshot.data;
//                return ListView(
//                  children: articles.map((article) => GestureDetector(
//                    onTap: (){
//
//                      _launchUrl(article.url);
//                    },
//                    child: Card(
//                      elevation:  1.0,
//                      color:  Colors.white,
//                      margin:  const EdgeInsets.symmetric(vertical: 8.0, horizontal: 8.0),
//                      child: Row(
//                        crossAxisAlignment: CrossAxisAlignment.start,
//                        children: <Widget>[
//                          Container(
//                            margin: const EdgeInsets.symmetric(vertical: 20.0, horizontal: 4.0),
//                            width: 100.0,
//                            height: 100.0,
//                            child: article.urlToImage != null ? Image.network(article.urlToImage): Image.asset("assets/nasa.jpg"),
//
//                          ),
//                          Expanded(
//                            child: Column(
//                              crossAxisAlignment: CrossAxisAlignment.start,
//                              children: <Widget>[
//                                Row(
//                                  children: <Widget>[
//                                    Expanded(
//
//                                      child: Container(
//                                        margin: const EdgeInsets.only(left: 8.0,top: 20.0,bottom: 10.0),
//                                        child: Text(
//                                          '${article.title}',
//                                          style: TextStyle(
//                                            fontSize: 16.0,
//                                            fontWeight: FontWeight.bold
//                                          ),
//
//                                        ),
//                                      ),
//                                    )
//                                  ],
//                                ),
//
//                                Container(
//                                  margin: const EdgeInsets.only(left: 8.0),
//                                  child: Text(
//                                    '${article.description}',
//                                    style: TextStyle(
//                                        fontSize: 12.0,
//                                        color: Colors.grey,
//                                        fontWeight: FontWeight.bold
//                                    )
//
//                                  ),
//                                ),
//
//
//                                Container(
//                                  margin: const EdgeInsets.only(left: 8.0, top: 10.0, bottom: 10.0),
//                                  child: Text(
//                                      'Pulished At: ${article.publishedAt}',
//                                      style: TextStyle(
//                                          fontSize: 12.0,
//                                          color: Colors.black12,
//                                          fontWeight: FontWeight.bold
//                                      )
//
//                                  ),
//                                ),
//
//                              ],
//                            ),
//
//                          )
//                        ],
//                      ),
//
//                    ),
//                  )).toList(),
//                );
//              }
//              return CircularProgressIndicator();
//
//            },
//
//          ),
//          onRefresh: refreshListArticle),
//
//
//        )
//      ),
//
//    );
//
//  }
//
//   _launchUrl(String url) async {
//     if (await canLaunch(url)) {
//       await launch(url);
//     } else {
//       throw ('Could not launch ${url}');
//     }
//   }
//
//}
//
//
//
