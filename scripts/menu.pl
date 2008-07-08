#!/usr/bin/perl

my $newmenu = "";
use Cwd;
my $cwdir = getcwd;
my $menuline;
my @langs;
my $replaced = 0;
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
        $replaced = 0;
        $html = '';
        if (open (HTML, "../$filename.$ext")) {
            print "reading ../$filename.$ext\n";
            while ($htmlline = <HTML>) {
                $html .= $htmlline;
            }
            close(HTML);
            my $html_orig = $html;
            $html =~ s/(<ul id="lang_choice">.*?<\/ul>)/$newmenu/sgmi;
            if ($html ne $html_orig) { $replaced = 1;}
            $html =~ s/($filename)/$filename/sgmi;
            if (!$replaced) {
                print "did not replace menu in $filename.$ext\n";
            }
            open (HTML, ">../$filename.$ext");
            #print "writing to ../$filename.$ext\n";
            print "\n";
            print HTML $html;
            close HTML;
        }
        else {
            warn "could not open ../$filename.$ext";
        }
    }
}
     
