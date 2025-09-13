#version 120

uniform sampler2D tex;
uniform float time;
uniform vec3 color;

void main() {
	vec2 uv = gl_TexCoord[0].st;
	
	uv.x = uv.x + sqrt(uv.y) - time * 0.7;

	float val = uv.y;
	uv.y = uv.y - time;

	vec4 clr = texture2D(tex, uv);	

	clr.w = clr.x * sqrt(1.01-val);

	clr.x = color.x;
	clr.y = color.y;
	clr.z = color.z;
    	
    	gl_FragColor = clr;
}