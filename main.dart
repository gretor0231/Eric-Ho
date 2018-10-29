import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:nasa/Model/Model.dart';
import 'dart:async';

Future<List<Source>> fetchNewsSource() async{


  final response =
    await http.get("https://launchlibrary.net/1.4/agency?name=National");

  if(response.statusCode == 200) //HTTP
  {
    List sources = json.decode(response.body)['sources'];
    return sources.map((source) => new Source.fromJson(source)).toList();
  }
  else
    {
      throw Exception('Failed to load source list');

    }
}

void main() => runApp(new SourceSccreen());

class SourceSccreen extends StatefulWidget{


  @override
  State<StatefulWidget> createState() => SourceSccreenState();


}

class SourceSccreenState extends State<SourceSccreen>{

  var list_sources;
  var refreshKey = GlobalKey<RefreshIndicatorState>();

  @override
  void initState() {
    super.initState();
    refreshListSource();
  }


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title:'Rocket SCHDUEL',
      theme: ThemeData(primarySwatch: Colors.blue),
      home:  Scaffold(
        appBar: AppBar(title: Text('Rocket time'),),
        body: Center(
          child: RefreshIndicator(
              key: refreshKey,
              child: FutureBuilder<List<Source>>(
                future: list_sources,
                builder: (context, snapshot){
                  if(snapshot.hasError){
                      return Text('Error: ${snapshot.error}');
                  }
                  else if(snapshot.hasData)
                    {
                      List<Source> sources = snapshot.data;
                      return new ListView(
                        children: sources.map((source) => GestureDetector(
                        onTap:(){
//                              something here
////                          Navigator.push(context, MaterialPageRoute(builder: (context) => ArticleScreen(source:source)));
//
                        },
                          child: Card(
                            elevation: 1.0,
                              color: Colors.white,
                              margin: const EdgeInsets.symmetric(vertical:8.0,horizontal:14.0),
//
                              child: Row(

                             ),
//
                          ),
//
                        )
//
                          ).toList());
//
                    }
                    return CircularProgressIndicator();
                },
              )
//
//
              , onRefresh: refreshListSource),
        ),

      ),

    );
  }

  Future<Null> refreshListSource() async{

    refreshKey.currentState?.show(atTop: false);

    setState(() {
      list_sources = fetchNewsSource();
    });
    return null;

  }



}
















