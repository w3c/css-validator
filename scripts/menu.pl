#!/usr/bin/perl

my $newmenu = "";
use Cwd;
my $cwdir = getcwd;
my $menuline;
my @langs;

open (menu_template, "menu.tmpl") or die("could not open menu template file");
while ($menuline = <menu_template>) {
     $newmenu .= $menuline; 
     if ($menuline =~ /xml:lang="(.*)"/){
         push @langs, $1;
     }
} 

my @files = ("manual.html", "about.html", "documentation.html", "DOWNLOAD.html");

my $html = "";
foreach my $filename (@files) {
    foreach my $ext (@langs) {
        if (open (HTML, "../$filename.$ext")) {
            $html = '';
            #print "reading ../$filename.$ext";
            while ($htmlline = <HTML>) {
                $html .= $htmlline;
            }
            close(HTML);
            $html =~ s/(<ul id="lang_choice">.*?<\/ul>)/$newmenu/sgmi;
            $html =~ s/($filename)/$filename/sgmi;
            open (HTML, ">../$filename.$ext");
            #print "writing to ../$filename.$ext";
            print HTML $html;
            close HTML;
        }
        else {
            die "could not open ../$filename.$ext";
        }
    }
}
     
