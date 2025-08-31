#version 120

uniform sampler2D tex;
uniform float time;

void main() {
	vec2 uv = gl_TexCoord[0].st;
	
	vec2 uvcr = uv - vec2(0.5,0.5);

    	float dist = length(uvcr);

    	vec2 rduv = vec2(dist*3.0 - time, abs(atan(uvcr.x/uvcr.y) / 3.1415926535897932384626433832795 * 2.0));

    	vec4 clr = texture2D(tex, rduv);
	
	clr.w = clr.x;

    	clr *= (1.0-dist*4.0*dist);

    	gl_FragColor = clr;
}