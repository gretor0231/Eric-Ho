

class NasaAPI{

  final String status;
  final List<Source> sources;

  NasaAPI({this.status, this.sources});

  factory NasaAPI.fromJson(Map<String, dynamic> json)
  {
    return NasaAPI( status: json['status'],
    sources: (json['sources'] as List).map((source) => Source.fromJson(source)).toList());


  }

}

class Source {

  final String id ;
  final String name ;
  final String countryCode ;
  final String abbrev ;
  final String type ;
  final String infoURL ;
  final String wikiURL ;
  final String infoURLs ;
  final String islsp;
  final String changed ;

  Source({this.id, this.name, this.countryCode, this.abbrev, this.type, this.infoURL, this.wikiURL, this.infoURLs, this.islsp, this.changed});




  factory Source.fromJson(Map<String,dynamic> json)
  {
    return Source (

    id: json['id'],
    name: json['name'],
      countryCode: json['countryCode'],
      abbrev: json['abbrev'],
      type: json['type'],
      infoURL: json['infoURL'],
      wikiURL: json['wikiURL'],
      infoURLs: json['infoURLs'],
      islsp: json['islsp'],
      changed: json['changed'],
    );

  }


}



